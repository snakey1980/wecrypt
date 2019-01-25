<template>
  <button class="button" @click="switchTeam">Switch team</button>
</template>

<script>
export default {
    name: 'Switcher',
    methods: {
        switchTeam: function() {
            this.$parent.switchTeam()
        }
    }
}
</script>

<style scoped>

</style>
