<template>
  <div>
    <input v-model="guess" class="input" type="text" :placeholder="this.team + ' guess here'"/>
    <button class="button is-success" @click="submitGuess(guess)">Submit guess</button>
  </div>
</template>

<script>
export default {
    name: 'Guesser',
    props: ['team'],
    data: function() {
        return {
            guess: ''
        }
    },
    methods: {
        submitGuess(guess) {
            this.$server.post("/" + this.team + "Guess", guess).then(r => {
                this.$parent.refresh()
            })
        }
    }
}
</script>

<style scoped>

</style>
