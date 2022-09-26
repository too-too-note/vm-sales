<template>
  <div id="app">
    <router-view/>
    <v-snackbar
        v-model="$store.state.snackBar"
        :color="$store.state.color"
    >
      <div>
        {{ $store.state.message }}
      </div>
    </v-snackbar>
  </div>
</template>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}
</style>
