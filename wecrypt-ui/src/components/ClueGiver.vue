<template>
  <div>
    <button class="button is-warning" @click="showCode()">Cluegiver only click here for code</button>
    <input v-model="clue1" class="input" type="text" placeholder="first clue here"/>
    <input v-model="clue2" class="input" type="text" placeholder="second clue here"/>
    <input v-model="clue3" class="input" type="text" placeholder="third clue here"/>
    <button class="button is-success" @click="submitClues([clue1, clue2, clue3])">Submit clues</button>
  </div>
</template>

<script>
export default {
    name: 'ClueGiver',
    props: ['code'],
    data: function() {
        return {
            clue1: '',
            clue2: '',
            clue3: ''
        }
    },
    methods: {
        showCode() {
            alert("The code for this turn is " + this.code)
        },
        submitClues(clues) {
            this.$server.post("/clue", clues).then(r => {
                this.$parent.refresh()
            })
        }
    }
}
</script>

<style scoped>

</style>
