import fetch from 'node-fetch';

export async function getAllMovies(store) {
    const movieList = []
    for (let index = 0; index < 10; index++) {
        const list = await fetch(`https://api.themoviedb.org/3/list/${index + 1}?api_key=7248330eaedc659a3fb3ab4ff9069bc2&language=en-US`)
            .then(response => response.json())
            .then(response => response.items)
            .then(movies => movies.map(m => m.title))
            .then(titles => titles.map(title => {
                return fetch(`https://www.omdbapi.com/?t=${title}&apikey=14183770`)
                    .then(response => response.json())
            }))
            .then(promises => Promise.all(promises))
            .then(list => list.map(m => {
                return {
                    title: m.Title,
                    year: Number.parseInt(m.Year),
                    overview: m.Plot,
                    rating: Number.parseFloat(m.imdbRating),
                    popularity: Number.parseInt(String(m.imdbVotes).replace(/\,/g, '')),
                    metascore: Number.parseInt(m.Metascore),
                    released: m.Released,
                    genre: String(m.Genre).split(', '),
                    poster: m.Poster,
                    director: m.Director,
                    writter: String(m.Writer).split(', '),
                    actors: String(m.Actors).split(', '),
                    type: 'movie'
                }
            }
            ))
        movieList.push(list);
    }

    const result = movieList.flat()

    // Filtre les doublons et les undefined
    return result.filter((value, index, self) =>
        index === self.findIndex((t) => (
            t.title === value.title
        ))
    ).filter(element => {
        return element.title !== undefined;
    });
}

// Fetch séries 
export async function getAllSeries() {

    // Fetch les listes de séries
    let loopedLists = []
    for (let index = 0; index < 5; index++) {
        loopedLists.push(await fetch(`https://api.themoviedb.org/3/tv/popular?api_key=7248330eaedc659a3fb3ab4ff9069bc2&language=en-US&page=${index + 1}`)
            .then(response => response.json())
            .then(response => response.results)
            .then(res => Promise.all(res)))
    }

    const test = await Promise.all(loopedLists)
    const seriesList = test.flat()

    // Get infos supplémentaires + nombre de saisons pour chaque série
    const seriesIds = seriesList.map(serie => serie.id);
    const seriesPromises = seriesIds.map(async id => {
        return fetch(`https://api.themoviedb.org/3/tv/${id}?api_key=7248330eaedc659a3fb3ab4ff9069bc2&language=en-US`)
            .then(response => response.json())
    });

    const series = await Promise.all(seriesPromises);

    // Get chacune des saisons de chaque série
    const seriesWithSeasonsPromises = series.map(async serie => {
        const seasonsPromises = Array(serie.number_of_seasons).fill(null).map(async (_, index) => {
            return fetch(`https://api.themoviedb.org/3/tv/${serie.id}/season/${index + 1}?api_key=7248330eaedc659a3fb3ab4ff9069bc2&language=en-US`)
                .then(response => response.json())
                .then(response => {
                    return {
                        id: response.id,
                        title: response.name,
                        seasonNumber: response.season_number,
                        infos: response.overview,
                        poster: "https://image.tmdb.org/t/p/w500" + response.poster_path,
                        episodes:
                            response.episodes ? response.episodes.map((episode, index) => {
                                return {
                                    epNumber: index + 1,
                                    title: episode.name,
                                    plot: episode.overview,
                                    rating: episode.vote_average,
                                    cast: episode.guest_stars.map(e => {
                                        return {
                                            name: e.name,
                                            character: e.character,
                                        }
                                    }),
                                    crew: episode.crew.map(e => {
                                        return {
                                            name: e.name,
                                            job: e.job,
                                        }
                                    }),
                                }
                            }) : null
                    }
                }
                )
        });

        let infos = seriesList.map(serie => {
            return {
                id: serie.id,
                plot: serie.overview,
                rating: serie.vote_average,
                popularity: serie.vote_count,
                poster: serie.poster_path,
            }
        })
        const seasonsCastPromises =
            await fetch(`https://api.themoviedb.org/3/tv/${serie.id}/season/1/credits?api_key=7248330eaedc659a3fb3ab4ff9069bc2&language=en-US`)
                .then(response => response.json())
                .then(res => res.cast.map(actor => actor.name))

        const seasons = await Promise.all(seasonsPromises);

        return {
            ...serie,
            seasons,
            cast: seasonsCastPromises,
            infos
        }
    })



    const seriesRefacto = await Promise.all(seriesWithSeasonsPromises);

    const lorem = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."

    return seriesRefacto.map((serie, index) => {
        return {
            title: serie.name,

            year: serie.last_air_date
                ? parseInt(serie.last_air_date.slice(0, 4))
                : -1,

            overview:
                serie.infos[index].plot
                    ? serie.infos[index].plot
                    : lorem,

            rating: serie.infos[index].rating
                ? serie.infos[index].rating
                : -1,

            popularity: serie.infos[index].popularity
                ? serie.infos[index].popularity
                : -1,

            released: serie.first_air_date
                ? serie.first_air_date
                : "N/A",

            genre: serie.genres.length > 0
                ? serie.genres.map(e => e.name)
                : ["N/A"],

            poster: serie.infos[index].poster === "N/A"
                ? "https://thumbs.dreamstime.com/b/ardoise-de-film-et-bobine-de-film-sur-le-bois-36502412.jpg"
                : "https://image.tmdb.org/t/p/w500" + serie.infos[index].poster,

            director: serie.created_by
                ? serie.created_by.map(e => e.name)
                : ["N/A"],

            actors: serie.cast
                ? serie.cast
                : ["N/A"],

            type: "serie",

            seasonCount: serie.seasons.length,

            seasons: serie.seasons
                ? serie.seasons
                : ["N/A"]
        }
    })
}