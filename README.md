# Expense Tracker API

**Masraf takibi ve aylık mali raporlama** için geliştirilmiş Spring Boot REST API. Kurumsal muhasebe / mali işler süreçlerinde departman bazlı harcama kaydı ve kategori özet raporları üretmeyi hedefler.

> **Geliştirici:** Yusuf Berat Bozkurt — Ondokuz Mayıs Üniversitesi Bilgisayar Programcılığı  
> **GitHub:** [yusufberatbozkurt8](https://github.com/yusufberatbozkurt8)

---

## Özellikler

- Masraf CRUD (oluştur, listele, güncelle, sil)
- Kategori filtresi (`OFFICE`, `TRAVEL`, `SUPPLIES`, `UTILITIES`, `PAYROLL`, `OTHER`)
- Aylık rapor: toplam tutar, kayıt sayısı, kategori kırılımı
- Bean Validation ile giriş doğrulama
- Swagger UI ile API dokümantasyonu
- H2 bellek içi veritabanı + örnek seed verisi

## Teknolojiler

| Katman | Teknoloji |
|--------|-----------|
| Backend | Java 17, Spring Boot 3.2 |
| Veri | Spring Data JPA, H2 |
| API | REST, OpenAPI (springdoc) |
| Test | JUnit 5, AssertJ |

## Kurulum

**Gereksinimler:** JDK 17+, Maven 3.9+

```bash
git clone https://github.com/yusufberatbozkurt8/expense-tracker-api.git
cd expense-tracker-api
mvn spring-boot:run
```

Uygulama: `http://localhost:8081`  
Swagger: `http://localhost:8081/swagger-ui.html`  
H2 Console: `http://localhost:8081/h2-console` (JDBC: `jdbc:h2:mem:expenses`)

## API Örnekleri

### Masraf oluştur

```http
POST /api/v1/expenses
Content-Type: application/json

{
  "title": "Yazıcı kartuş",
  "description": "Ofis sarf",
  "amount": 450.75,
  "category": "SUPPLIES",
  "expenseDate": "2026-06-01",
  "department": "Muhasebe"
}
```

### Aylık rapor

```http
GET /api/v1/expenses/reports/monthly?year=2026&month=6
```

### Kategoriye göre listele

```http
GET /api/v1/expenses?category=TRAVEL
```

## Proje yapısı

```
src/main/java/com/yusufberat/expensetracker/
├── controller/   # REST uçları
├── service/      # İş kuralları ve raporlama
├── repository/   # JPA sorguları
├── model/        # Expense entity
├── dto/          # Request/Response kayıtları
└── config/       # Örnek veri yükleyici
```

## Test

```bash
mvn test
```

## Portföy notu

Bu proje, staj ve iş başvurularında **mali işler otomasyonu** ve **Spring Boot REST** yetkinliğini göstermek için tasarlanmıştır. Gerçek ortamda PostgreSQL ve Spring Security ile genişletilebilir.

## Lisans

MIT
