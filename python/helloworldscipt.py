import pandas as pd
import csv
from sklearn.neighbors import NearestNeighbors
from os.path import dirname, join
from scipy.sparse import csr_matrix
from fuzzywuzzy import fuzz

#item-based collaborative filtering recommender with KNN

def recommend(param):
    filename1 = join(dirname(__file__), "movies2.csv")
    filename2 = join(dirname(__file__), "moviesPerClients2.csv")

    nr_movies=int(param[0])
    fav_movie = param[1]
    rowsMovies = param[2]
    rowsMoviesPerClients = param[3]
    db_movies = param[4].split(',')
    db_moviesPerClients = param[5].split(',')
    
    # table( ID, title) with all movies
    with open(filename1, 'w', newline='') as csvfile:
        fieldnames = [db_movies[0], db_movies[1]]
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        for i in range(0, int(rowsMovies), 2):
            writer.writerow({db_movies[0]: db_movies[2 + i], db_movies[1]: db_movies[2 + i + 1], })

    db_movies = pd.read_csv(filename1)
    print(db_movies)

    # table( client_id, movie_id, movie_rating) with all movies added by users
    with open(filename2, 'w', newline='') as csvfile:
        fieldnames = [db_moviesPerClients[0], db_moviesPerClients[1], db_moviesPerClients[2]]
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        for i in range(0, int(rowsMoviesPerClients), 3):
            writer.writerow({db_moviesPerClients[0]: db_moviesPerClients[3 + i ],
                             db_moviesPerClients[1]: db_moviesPerClients[3 + i + 1],
                             db_moviesPerClients[2]: db_moviesPerClients[3 + i + 2]})
    
    db_moviesPerClients = pd.read_csv(filename2)
    print(db_moviesPerClients)
    # we filter popular movies based by users
    # pivot ratings into movie features( a matrix with movies as rows and users as columns)
    db_movie_features=db_moviesPerClients.pivot(
                        index='MOVIE_ID',
                        columns='CLIENT_ID',
                        values='MOVIE_RATING',).fillna(0)
   
    # transform matrix to scipy sparse matrix
    movie_user_mat_sparse = csr_matrix(db_movie_features.values)  
    # matrix example
    # user_id   2   3
    # movie_id
    # 1         3.0 2.0
    # 2         4.0 1.0
    
    # create mapper from movie title to index
    hashmap = {
            movie: i for i, movie in
            enumerate(list(db_movies.set_index('MOVIE_ID').loc[db_movie_features.index].MOVIE_NAME)) # noqa
    }
    
    # KNN calculates the distance between the target movie and every other movie in the database
    # then ranks its distances and returns top K nearest neighbor movies as the most similar movie recommendations
    # we use cosine similarity( cosinus between 2 vectors) because gives better performance than euclidean distance
    # brute-force search
    # n_jobs=-1 (use all processors)
    # n_neighbors= number of neighbors
    model_knn = NearestNeighbors(metric='cosine', algorithm='brute', n_neighbors=nr_movies, n_jobs=-1)

    # inference made with k nearest neighbors
    raw_recommends=inference(model_knn,movie_user_mat_sparse,hashmap,fav_movie,int(nr_movies/2))
    
    # print results
    recommendations=[]
    reverse_hashmap = {v: k for k, v in hashmap.items()}
    print('Recommendations for {}:'.format(fav_movie))
    for i, (idx, dist) in enumerate(raw_recommends):
            print('{0}: {1}, with distance '
                  'of {2}'.format(i+1, reverse_hashmap[idx], dist))
            recommendations.append(reverse_hashmap[idx])
                  
    #returns movie recommendations list 
    return recommendations

# returns the closest match via fuzzy ratio
# search for the input movie using Levenshtein distance( metric which measures how apart are 2 word sequences)
def fuzzy_matching(hashmap,fav_movie):
    match_tuple = []
    # get match
    for title, idx in hashmap.items():
        ratio = fuzz.ratio(title.lower(), fav_movie.lower()) # kind of equals
        if ratio >= 60:
            match_tuple.append((title, idx, ratio))
    # sort
    match_tuple = sorted(match_tuple, key=lambda x: x[2])[::-1]
    if not match_tuple:
        print('No match is found')
    else:
        print('Found possible matches in our database: '
                  '{0}\n'.format([x[0] for x in match_tuple]))
        return match_tuple[0][1]


def inference(model,data,hashmap,fav_movie,n_recommendations):
    # fit
    model.fit(data)
    # get input movie index
    print('User\'s favourite movie:', fav_movie)
    idx = fuzzy_matching(hashmap, fav_movie)
    print(idx)
    # inference
    print('Recommendation system starts to make inference')
    print('......\n')
    # gets neighbors by distance
    if idx==None:
        return []
    else:
        distances, indices = model.kneighbors(
                data[idx],
                n_neighbors=n_recommendations)
        # get list of raw idx of recommendations
        raw_recommends = \
                sorted(
                    list(
                        zip(
                            indices.squeeze().tolist(),
                            distances.squeeze().tolist()
                        )
                    ),
                    key=lambda x: x[1]
                )[:0:-1]
        # return recommendations (movieId, distance)
        return raw_recommends