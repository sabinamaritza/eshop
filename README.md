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

<br>

### Module 2 • CI/CD & DevOps
<details>
  <summary><h4>Reflection</h4></summary>
</details>

<br>

### Module 3 • Maintainability & OO Principles
<details>
  <summary><h4>Reflection</h4></summary>
  1. Explain what principles you apply to your project! <br>
  Pada proyek ini telah diaplikasikan semua prinsip SOLID. <br>
  - <b>Single Responsibility Principle (SRP)</b>: SRP menyatakan bahwa setiap kelas dan method hanya memiliki satu tanggungjawab. Setelah dibenarkan, semua kelas hanya meng-handle 1 hal. Car model hanya berfungsi untuk representasi Car, CarRepository untuk meng-handle data, CarService untuk logic, dan CarController untuk HTTP requests. Controller juga telah dibagi 2, satu untuk meng-handle HTTP request Product dan satunya untuk HTTP request Car. <br>
  - <b>Open Closed Principle (OCP)</b>: Untuk mengaplikasikan OCP, telah dilakukan abstraksi, dimana untuk interface CarRepository dan CarService, terdapat kelas CarRepositoryImpl dan CarServiceImpl yang mengimplementasikannya. Ini memungkinkan extension tanpa modification. <br>
  - <b>Liskov Substitution Principle (LSP)</b>: LSP menyatakan bahwa semua objek subclass yang mengimplementasi base class, harus dapat menggantikan base class tersebut tanpa ada perubahan fungsionalitas. Ini ditunjukkan dengan interface: CarRepository dan CarService, beserta kelas implementasinya: CarRepositoryImpl dan CarServiceImpl. <br>
  - <b>Interface Segregation Principle (ISP)</b>: Pada ISP, dinyatakan bahwa suatu kelas tidak perlu bergantung kepada method yang tidak digunakan. Misalnya, pada before-solid, controller untuk Product dan Car digabung, sehingga Car harus bergantung pada Product.
  <br>
  2. Explain the advantages of applying SOLID principles to your project with examples. <br>
  
  <br>
  3. Explain the disadvantages of not applying SOLID principles to your project with examples. <br>
  
</details>
