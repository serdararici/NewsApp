# Haber Uygulaması

Bu proje, kullanıcıların haberleri ve duyuruları listeleyebildiği bir mobil haber uygulamasıdır. Uygulama, farklı kullanıcı rolleri (kullanıcı ve admin) içermektedir ve her rolün farklı yetkileri bulunmaktadır.


![NewsApp](https://github.com/serdararici/NewsApp/blob/master/Poster.JPG)


## Özellikler

### Kullanıcı Rolleri
- **Admin**: Giriş yaptıktan sonra haber ve duyuruları ekleyebilir, düzenleyebilir ve silebilir.
- **Kullanıcı**: Giriş yaptıktan sonra sadece haber ve duyuruları listeleyebilir.

### Kayıt İşlemleri
- Admin kullanıcıları sistem tarafından atanır ve direkt veritabanına eklenir.
- Kullanıcılar, uygulamaya giriş yaptıktan sonra kayıt ol sayfasında e-posta, kullanıcı adı ve şifre ile kayıt olurlar.
- Kayıt işlemi tamamlandıktan sonra, kullanıcılar giriş yaparak uygulamaya erişebilirler.

### Arama Özelliği
- Haberler arasında arama yapabilme özelliği ile kullanıcılar, istedikleri haberi hızlıca bulabilirler.

## Kullanılan Teknolojiler
- **Kotlin**: Uygulama geliştirme için kullanılan ana programlama dili.
- **MVVM Design Pattern**: Uygulamanın mimarisinde kullanılan Model-View-ViewModel tasarım deseni, uygulamanın daha düzenli ve sürdürülebilir olmasını sağlar.
- **Room**: Yerel veritabanı işlemleri için kullanılan bir kütüphane, verilerin yönetimi ve kalıcılığı için kolay bir yol sunar.
- **SQLite**: Uygulama içerisinde veri depolamak için kullanılan ilişkisel veritabanı yönetim sistemi.
- **Fotoğraf İşlemleri**: Uygulama, kullanıcıların eklediği içeriklerde fotoğrafları gösterir ve kaydeder.
- **Dosyaya Kaydetme**: Uygulama, fotoğrafları dosya sistemine kaydetme işlemleri gerçekleştirir.
- **Authentication İşlemleri**: Kullanıcıların güvenli bir şekilde giriş yapabilmesi için kimlik doğrulama işlemleri uygulanır.

## Öğrenilen Bilgiler
Bu projeyi geliştirirken, aşağıdaki konularda deneyim kazandım:
- Kotlin dilinin özellikleri ve uygulama geliştirmedeki kullanımı.
- MVVM tasarım deseninin avantajları ve nasıl uygulanacağı.
- Room ve SQLite ile yerel veritabanı işlemlerinin yönetimi.
- Fotoğraf ve dosya işlemleri ile ilgili uygulama geliştirme.
- Kullanıcı kimlik doğrulama işlemleri ile güvenli bir uygulama geliştirme.
