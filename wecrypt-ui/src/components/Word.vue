<template>
  <span>
    <span class="word" v-if="this.team === 'defense'">{{ word }}</span>
    <span class="word" v-if="this.team === 'offense'">????</span>
  </span>
</template>

<script>
export default {
    name: 'Word',
    props: ['word', 'team']
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
span.word {
  font-family: 'Special Elite', cursive;
  font-size: 40pt;
}
</style>
