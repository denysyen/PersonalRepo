<template>
  <div class="vod-media-details">
    <VodMediaPoster :mediaPoster="this.getMediaByTitle(title).poster" />
    <VodMediaSummary :mediaTitle="title" :mediaYear="this.getMediaByTitle(title).year"
      :mediaType="this.getMediaByTitle(title).type" :mediaNbSeason="this.getMediaByTitle(title).seasonCount"
      :mediaDirectors="this.getMediaByTitle(title).director" :mediaActors="this.getMediaByTitle(title).actors"
      :mediaOverview="this.getMediaByTitle(title).overview" />
    <div class="vod-detail-back-button">
      <VodGoBackButton @keyup="navigation($event)" ref="vodBackBtn" :mediaTitle="title" />
    </div>
  </div>
</template>
<script>
import VodMediaPoster from '@/components/VodMediaPoster.vue'
import VodMediaSummary from '@/components/VodMediaSummary.vue'
import VodGoBackButton from "@/components/VodGoBackButton.vue"
import { mapGetters } from 'vuex'
import { mapActions } from "vuex"

export default {
  name: "VodDetailsView",

  components: {
    VodMediaPoster,
    VodMediaSummary,
    VodGoBackButton
  },
  beforeMount() {
    this.getAllMovies();
    this.getAllSeries();
    this.getAllMedias();
  },
  mounted() {
    this.$refs.vodBackBtn.focus();
    window.addEventListener('keyup', this.navigation, null);
  },
  beforeUnmount() {
    window.removeEventListener('keyup', this.navigation);
  },
  data() {
    return {
      mediaPoster: '',
      mediaTitle: '',
      mediaYear: 0,
      mediaType: '',
      mediaNbSeason: 0,
      mediaDirectors: '',
      mediaActors: '',
      mediaOverview: ''
    }
  },
  computed: {
    title() {
      return this.$route.params.title;
    },
    ...mapGetters(["getMovieList", "getSerieList", "getMedias", "getMediaByTitle"])
  },
  methods: {
    ...mapActions(["getAllMovies", "getAllSeries", "getAllMedias"]),
    navigation(e) {
      switch (e.keyCode) {
        case 38: //38 = up
          document.getElementsByTagName("a")[0].focus();
          break;
        case 40: //40 = down
          document.getElementsByClassName("vod-go-back-btn")[0].focus();
          break;
      }
    }
  }
}
</script>
<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,400;0,500;0,700;1,400;1,500&display=swap');

body {
  overflow-x: hidden;
  overflow-y: hidden;
}

template {
  display: flex;
}

.vod-media-details {
  margin: 0% 5% 20%;
  position: unset;
  background-color: #d2c9bf;
  display: flex;
  flex-wrap: wrap;
  flex-direction: row;
}
.vod-detail-back-button {
  text-align: center;
}
</style>