<template>
  <div class="films-view">
  <main>
  <Rail :page="'films'" :sortingProp="sortingProp" />
  </main>
  <footer>
    <!-- Récupère la valeur du filtre reçue par la wheel pour la transmettre dans la grid -->
    <UiWheel @clicked="getEmitedChildValue" :page="'films'" />
  </footer>
  </div>
</template>

<script>
import UiWheel from "@/components/Wheel.vue";
import Rail from "@/components/Rail.vue";
export default {
  data() {
    return {
      sortingProp: "",
    };
  },
  name: "FilmsView",
  components: {
    UiWheel,
    Rail,
  },
  methods: {
    // Récupère la valeur cliquée sur le menu filtre et l'enregistre dans le state
    getEmitedChildValue(value) {
      this.sortingProp = value;
    },
  },
};
</script>

<style>
</style>