<script>
  import './styles.scss';
  let name = 'world';
</script>

<main>
  <h1>Hello ... {name}!</h1>
  <input bind:value={name} placeholder="Enter your name" />
</main>