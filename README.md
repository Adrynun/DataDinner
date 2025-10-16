# 🍽️ DataDinner — Backend (Java + Spring Boot)

![Java](https://img.shields.io/badge/Java-17-red?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8-blue?style=flat-square&logo=mysql)
![License](https://img.shields.io/badge/license-MIT-lightgrey?style=flat-square)
![Status](https://img.shields.io/badge/status-En%20Desarrollo-orange?style=flat-square)

---

## 🧩 Descripción general

**DataDinner** es un sistema de gestión para restaurantes, desarrollado en **Java con Spring Boot**.  
Este backend maneja toda la lógica relacionada con **usuarios, mesas, pedidos, productos y facturación**.

Su arquitectura está basada en una estructura **limpia y modular**, separando claramente:
- **Entidades (Model)**
- **Repositorios (Repository)**
- **Servicios (Service)**
- **Controladores (Controller)**  

Incluye generación de **tickets PDF** con **JasperReports**, y endpoints REST listos para integración con un **frontend Angular**.

---

## 🏗️ Arquitectura general

src/
├─ main/
│ ├─ java/com/datadinner/
│ │ ├─ controller/
│ │ ├─ service/
│ │ ├─ repository/
│ │ └─ model/
│ └─ resources/
│ ├─ application.properties
│ └─ reports/ticket.jrxml
└─ test/

php-template
Copiar código

---

## 🗂️ Entidades principales

<details>
<summary><b>👤 Usuario</b></summary>

- Roles: `CAMARERO`, `ENCARGADO`, `ADMIN`
- Utilizado para autenticación y trazabilidad (quién toma pedidos, genera facturas, etc.)

</details>

<details>
<summary><b>🍽️ Mesa</b></summary>

- Representa una mesa del restaurante  
- Atributos: número, estado (`DISPONIBLE`, `OCUPADA`, etc.)

</details>

<details>
<summary><b>🧾 Producto</b></summary>

- Representa platos o bebidas del menú  
- Atributos: nombre, precio, categoría

</details>

<details>
<summary><b>🧺 Pedido</b></summary>

- Pedido realizado por un usuario en una mesa  
- Estado: `EN_PREPARACION`, `SERVIDO`, `CERRADO`  
- Relaciones:
  - Muchos-a-uno con **Usuario** y **Mesa**
  - Uno-a-muchos con **PedidoProducto**

</details>

<details>
<summary><b>📦 PedidoProducto</b></summary>

- Tabla intermedia entre Pedido y Producto  
- Incluye cantidad y precio unitario  
- Permite calcular subtotales y totales del pedido

</details>

<details>
<summary><b>💵 Factura</b></summary>

- Generada al cerrar un pedido  
- Contiene total, fecha y campo `cerrada`  
- Servirá para el **resumen de caja diario**

</details>

---

## ⚙️ Servicios principales

| Servicio | Descripción |
|-----------|--------------|
| **PedidoService** | CRUD completo de pedidos, cambio de estado, gestión de productos asociados |
| **FacturaService** | Genera factura a partir de pedido, calcula totales, lista y cierra facturas |
| **JasperReportService** | Genera ticket PDF desde plantilla `.jrxml` usando JasperReports |

---

## 🌐 Endpoints REST

| Método | Endpoint | Descripción |
|--------|-----------|-------------|
| `POST` | `/facturas/generar/{pedidoId}` | Genera una factura para un pedido |
| `GET` | `/facturas/pendientes` | Lista facturas no cerradas |
| `POST` | `/facturas/cerrar/{id}` | Marca una factura como cerrada |
| `GET` | `/facturas/ticket/{pedidoId}` | Devuelve el ticket PDF del pedido |

---

## 🧰 Dependencias clave

```xml
<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- MySQL -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
    </dependency>

    <!-- JasperReports -->
    <dependency>
        <groupId>net.sf.jasperreports</groupId>
        <artifactId>jasperreports-pdf</artifactId>
        <version>7.0.1</version>
    </dependency>
    <dependency>
        <groupId>com.itextpdf</groupId>
        <artifactId>itextpdf</artifactId>
        <version>5.5.13.4</version>
    </dependency>
</dependencies>
⚙️ Configuración de entorno
Crear una base de datos MySQL (por ejemplo, datadinner_db).

Configurar el archivo application.properties:

properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/datadinner_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Ejecutar el proyecto con:

bash
Copiar código
mvn spring-boot:run
Backend disponible en:
👉 http://localhost:8080

🧾 Generación de tickets PDF
El servicio usa JasperReports para generar tickets de pedido:

Plantilla: src/main/resources/reports/ticket.jrxml

Contiene datos de:

Mesa, camarero, fecha/hora

Lista de productos con cantidad y precio

Total del pedido

Retorna los bytes PDF para descarga o visualización en el frontend Angular.

🚀 Estado del proyecto
Estado	Descripción
✅	CRUDs completos para todas las entidades
✅	Generación de tickets PDF funcional
⚙️	Pendiente: cierre de caja diario (sumatorio de facturas)
🔄	Integración completa con frontend Angular

📄 Licencia
Este proyecto está bajo la licencia MIT.
Puedes usarlo, modificarlo y distribuirlo libremente, siempre que se mantenga el aviso de licencia.

💡 Autor
DataDinner Team
📧 Contacto: soporte@datadinner.com (opcional)
