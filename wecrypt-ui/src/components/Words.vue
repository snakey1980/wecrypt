<template>
  <div>
    <div class="columns is-centered">
      <div class="column" v-for="word in this.uistate.words" v-bind:key="word"><Word :word="word" :team="team"></Word></div>
    </div>
  </div>
</template>

<script>
import Word from './Word.vue'
export default {
    name: 'Words',
    props: ['team', 'uistate'],
    components: {
        Word
    }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
