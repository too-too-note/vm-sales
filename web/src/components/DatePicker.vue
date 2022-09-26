<template>
  <v-menu
      ref="menu"
      v-model="menu"
      :close-on-content-click="false"
      :return-value.sync="dateValue"
      transition="scale-transition"
      offset-y
      min-width="auto"
  >
    <template v-slot:activator="{ on, attrs }">
      <v-text-field
          v-model="dateValue"
          v-if="!multiple"
          :label="label"
          prepend-icon="mdi-calendar"
          readonly
          v-bind="attrs"
          v-on="on"
          hide-details
          :disabled="disabled"
      ></v-text-field>
      <v-combobox
          v-model="dateValue"
          v-else
          multiple
          chips
          small-chips
          :label="label"
          prepend-icon="mdi-calendar"
          readonly
          hide-details
          v-bind="attrs"
          v-on="on"
          :disabled="disabled"
      ></v-combobox>
    </template>
    <v-date-picker
        v-model="dateValue"
        no-title
        scrollable
        :multiple="multiple"
        @change="save"
    >
      <v-spacer></v-spacer>
      <v-btn
          text
          color="primary"
          @click="menu = false"
      >
        Cancel
      </v-btn>
      <v-btn
          text
          color="primary"
          @click="save"
      >
        OK
      </v-btn>
    </v-date-picker>
  </v-menu>
</template>

<script>
export default {
  props: {
    value: {},
    label: {},
    multiple: { default: false },
    disabled: { default: false}
  },
  data() {
    return {
      menu: false,
      dateValue: "",
    }
  },
  mounted() {
    this.dateValue = this.$props.value
  },
  methods: {
    save() {
      this.$refs.menu.save(this.dateValue)
      this.$emit('input', this.dateValue)
      this.$emit('change')
      this.menu = false
    },
  },
}
</script>

<style scoped>

</style>