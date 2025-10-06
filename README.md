# 🍽️ ms-menu

**Application Name:** `ms-menu`  
**Description:** Menu Management Microservice

---

## 📘 Ümumi Məlumat

Bu microservice restoranlara aid menyuların idarə olunmasını təmin edir.  
Hər bir menyu müəyyən restorana məxsus olur və **ms-restaurant** servisi ilə **Feign Client** vasitəsilə əlaqə qurur.

---

## ⚙️ Əsas Məntiq

### 🧩 Menu
- Menyuların **yaradılması**, **yenilənməsi**, **silinməsi** və **siyahılanması** (CRUD əməliyyatları).
- Menyu yaradılarkən `restaurantId` yoxlanır — Feign Client vasitəsilə **ms-restaurant** servisinə `GET` sorğusu atılır.
- Əgər restoran mövcud deyilsə, `NotFoundException` atılır.
- Qiymət, başlıq (title), mövcudluq (available) kimi sahələr saxlanılır.

### 🔗 Feign Client Integration
- **Feign Client** `http://localhost:8081/api/v1/restaurants/{id}` endpointinə sorğu ataraq restoranın mövcudluğunu yoxlayır.
- Əgər restoran varsa, menyu həmin restoran üçün DB-yə yazılır.
- Əgər restoran yoxdur, `RuntimeException` (və ya `NotFoundException`) qaytarılır.

### 💳 Order Food (Payment Integration)
- `orderFood` metodu menyudan seçilmiş məhsulların qiymətlərini toplayır.
- Toplam məbləğ və `userId` **Payment Microservice**-ə Feign Client vasitəsilə göndərilir.
- Payment servisi balansı yoxlayır və nəticəni geri qaytarır.

---

## 🛠️ İstifadə Olunan Texnologiyalar

| Texnologiya | Məqsəd |
|--------------|--------|
| **Spring Boot 3** | Framework və tətbiq infrastrukturu |
| **Spring Data JPA** | ORM və DB əməliyyatları |
| **PostgreSQL** | Əsas verilənlər bazası |
| **Feign Client** | Servislərarası HTTP inteqrasiyası |
| **Liquibase** | DB dəyişikliklərinin idarə olunması |
| **Lombok** | Kodun sadələşdirilməsi |
| **Docker** | PostgreSQL və digər konteynerlərin idarəsi |

---

## 🔁 İş Axını (Workflow)

1. **Yeni menyu yaradılır**  
   → `restaurantId` ilə Feign Client vasitəsilə **ms-restaurant** yoxlanılır.  
   → Əgər restoran mövcuddursa → menyu DB-yə yazılır.

2. **Müştəri sifariş verir (orderFood)**  
   → Seçilmiş menyu id-ləri ilə qiymətlər toplanır.  
   → Feign Client ilə **ms-payment** servisinə sorğu göndərilir.  
   → Əgər balans kifayətlidirsə → ödəniş təsdiqlənir.

---

## 🧠 Niyə belə dizayn seçilib?

- **Feign Client** sayəsində servislər bir-biri ilə REST vasitəsilə əlaqə saxlayır.
- **Modular yanaşma:** hər servisin öz məsuliyyət dairəsi var.
- **Error Handling:** GlobalExceptionHandler ilə daha aydın və idarə olunan xətalar.
- **DB nəzarəti:** Liquibase ilə dəyişikliklərin izlənməsi.

---

## ✅ Nəticə

`ms-menu` servisi restoran menyularını idarə edir, Feign Client vasitəsilə restoranları yoxlayır və **payment** ilə inteqrasiya olunur.  
Bu layihə microservice arxitekturasında **servislərarası əlaqənin** ən sadə, lakin effektiv nümunəsidir.

