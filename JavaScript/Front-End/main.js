import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// fontawesome
import { library } from '@fortawesome/fontawesome-svg-core'
import { faTv, faFilm, faHouse, faChevronRight, faChevronLeft, faStar } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(faTv, faFilm, faHouse, faChevronRight, faChevronLeft, faStar)

createApp(App)
.use(store)
.use(router)
.component("font-awesome-icon", FontAwesomeIcon)
.mount('#app')
