# Reflection

### Module 1 • Coding Standards
<details>
  <summary><h4>Reflection 1</h4></summary>
  Pada program ini, telah di terapkan clean code dimana penamaan metode, fungsi, dan variabel jelas menyatakan tujuannya. Ini dibuat agar kode dapat dengan mudah dimengerti semua orang tanpa adanya komen yang berlebih. Penerapan clean code juga dapat dilihat dari method-method yang hanya memiliki satu task, seperti untuk membuat produk, meng-update produk, atau menghapus produk. Penggunaan @Getter dan @Setter dari library Lombok juga membantu mengurangi kode yang repetitif. 
  <br><br>
  Untuk security, terdapat input validation di client side menggunakan atribut yang tersedia di HTML untuk mengurangi adanya invalid input. Misal pada CreateProduct.html, terdapat tag required dan min, serta fungsi validateForm untuk mengecek apakah variabel quantity bersifat angka yang tidak kurang dari 0. Penggunaan ID pada Product juga diubah sehingga menggunakan UUID yang lebih secure.
  <br><br>
  Mungkin kekurangan yang masih terdapat di kode adalah penggunaan null sebagai exception handling pada bagian findById. Jika ini tidak ter-handle dengan baik, bisa menyebabkan error NullPointerException. Perbaikan yang dapat dilakukan adalah dengan menggunakan Optional Method.
</details>
