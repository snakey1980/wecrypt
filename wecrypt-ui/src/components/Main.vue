<template>
  <section class="section">
    <div class="container">
      <Words :team="this.team" :uistate="this.uistate"/>
      <Status :status="this.uistate.state"/>
      <div v-if="this.uistate.needsClue && this.team === 'defense'">
        <ClueGiver :code="this.uistate.code"/>
      </div>
      <div v-if="this.uistate.needsDefenseGuess && this.team === 'defense'">
        <Guesser :team="'defense'"/>
      </div>
      <div v-if="this.uistate.needsOffenseGuess && this.team === 'offense'">
        <Guesser :team="'offense'"/>
      </div>
      <Switcher/>
    </div>
  </section>
</template>

<script>
    import ClueGiver from './ClueGiver.vue'
    import Guesser from './Guesser.vue'
    import Status from './Status.vue'
    import Switcher from './Switcher.vue'
    import Words from './Words.vue'
    export default {
        name: 'Main',
        components: {
            ClueGiver,
            Guesser,
            Status,
            Switcher,
            Words
        },
        data: function() {
            return {
                team: 'defense',
                uistate: {}
            }
        },
        created() {
            this.refresh();
            setInterval(function() {
                  this.refresh();
                }.bind(this), 5000);
        },
        methods: {
            refresh() {
                this.$server.get('/getState').then(r => {
                    this.uistate = r.data
                })
            },
            switchTeam() {
                if (this.team === 'defense') {
                    this.team = 'offense'
                }
                else {
                    this.team = 'defense'
                }
            }
        }
    }
</script>

<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
