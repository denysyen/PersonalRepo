<template>
<main>
  <Rail :page="'home'" :sortingProp="sortingProp" />
    </main>
  <footer>
    <UiWheel @clicked="getEmitedChildValue" :page="'home'" />
  </footer>
</template>
<script>
// @ is an alias to /src
import UiWheel from "@/components/Wheel.vue";
import Rail from "@/components/Rail.vue";
import { mapActions } from "vuex";
export default {
  data() {
    return {
      sortingProp: "",
    };
  },
  name: "HomeView",
  components: {
    Rail,
    UiWheel
  },

  beforeMount() {
    this.getAllMovies();
    this.getAllSeries();
    this.getAllMedias();
  },
  methods: {
    ...mapActions(["getAllMovies", "getAllSeries", "getAllMedias"]),
    // Récupère la valeur cliquée sur le menu filtre et l'enregistre dans le state
    getEmitedChildValue(value) {
      this.sortingProp = value;
    },
  },
};
</script>

<style>
footer {
  text-align: -webkit-center;
}
</style>