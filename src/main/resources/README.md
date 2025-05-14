
# React

## install react
```shell
npx create-react-app  app-react
cd app-react
npm run start
```

## install scss
```shell
# TODO
```

## install bootstrap
```shell
# TODO
```



----

# Vite + Svelte

## install svelte
```
npm create vite@latest app-vite-svelte -- --template svelte

cd app-vite-svelte
npm install
npm run dev
```


## install scss
```
npm install -D sass
```


## Create an SCSS File:
Create a new SCSS file, e.g., src/styles.scss:

```scss
// src/styles.scss

$primary-color: #3498db;

body {
    font-family: Arial, sans-serif;
}

h1 {
    color: $primary-color;
}
```

## Import SCSS in Your Svelte Component:
Update App.svelte to import the SCSS file:
```svelte
<script>
    import './styles.scss';
    let name = 'world';
</script>

<main>
    <h1>Hello {name}!</h1>
    <input bind:value={name} placeholder="Enter your name" />
</main>
```


## Build the Project:
When you're ready to deploy, run:
```
npm run build
```
This will generate the production-ready files in the dist directory.




