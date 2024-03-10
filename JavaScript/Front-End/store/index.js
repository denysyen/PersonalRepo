import { createStore } from 'vuex'
import mediaList from "../../src/assets/localAPIModel.json";

const store = createStore({
  state: {
    movies: [],
    series: [],
    medias: [],
  },
  getters: {
    getMedias(state) {
      return state.medias
    },
    getMovieList(state) {
      return state.movies
    },

    getSerieList(state) {
      return state.series
    },

    getMediaListProp: (state) => (prop) => {
      if (prop === 'home') {
        return new Array('Trending', 'Best', 'Popular', 'Random')
      }

      if (prop === 'films') {
        const categoryListTmp = new Set();
        state.movies.forEach(movie => {
          if (movie.type === "movie" && movie.genre.length > 0)
            movie.genre.forEach(e => categoryListTmp.add(e))

        })
        return Array.from(categoryListTmp).sort()
      }

      if (prop === 'series') {
        const categoryListTmp = new Set();
        state.series.forEach(serie => {
          if (serie.type === "serie" && serie.genre.length > 0)
            serie.genre.forEach(e => categoryListTmp.add(e))

        })
        return Array.from(categoryListTmp).sort()
      }
    },

    getFilteredMoviesList: (state) => (prop) => {
      const filteredList = []
      state.movies.forEach(movie => {
        if (movie.genre.length > 0)
          movie.genre.
            forEach(e => {
              if (e === prop) {
                filteredList.push(movie)
              }
            })
      })
      return filteredList;
    },

    getFilteredSeriesList: (state) => (prop) => {
      const filteredList = []
      state.series.forEach(serie => {
        if (serie.genre.length > 0)
          serie.genre.
            forEach(e => {
              if (e === prop) {
                filteredList.push(serie)
              }
            })
      })
      return filteredList;
    },
    getFilteredHomeSelection: (state) => (prop) => {
      switch (prop) {

        case "Random":
          let randomList = [];
          for (let index = 0; index < 20; index++) {
            randomList.push(
              state.medias[Math.floor(Math.random() * state.medias.length)])
          }
          return randomList;


        case "Trending":
          return state.medias
            .sort((a, b) => b.year - a.year)
            .slice(0, 20);

        case "Best":
          return state.medias
            .sort((a, b) => b.rating - a.rating)
            .slice(0, 20);

        case "Popular":
          return state.medias
            .sort((a, b) => b.popularity - a.popularity)
            .slice(0, 20);
      }
    },
    getMediaByTitle: (state) => (title) => mediaList.find(media => media.title === title)

  },

  mutations: {
    ON_MEDIAS_FETCHED(state, medias) {
      state.medias = medias;
    },
    ON_MOVIES_FETCHED(state, movies) {
      state.movies = movies;
    },
    ON_MOVIES_FETCH_ERROR(state, error) {
      state.lastErrorMessage = error.message
    },
    ON_SERIES_FETCHED(state, series) {
      state.series = series;
    },
  },

  actions: {
    async getAllMedias(store) {
      const finalList = mediaList.flatMap(film => film.title ? film : []);

      store.commit(
        'ON_MEDIAS_FETCHED',
        finalList
      );
    },

    async getAllMovies(store) {
      const finalList = mediaList.flatMap(film => film.type === "movie" ? film : []);
      store.commit(
        'ON_MOVIES_FETCHED',
        finalList
      );
    },

    async getAllSeries(store) {
      const finalList = mediaList.flatMap(serie => serie.type === "serie" ? serie : []);
      store.commit(
        'ON_SERIES_FETCHED',
        finalList
      );
    },
  },
})

export default store