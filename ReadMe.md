# UYGULAMADA KULLANILACAK AYARLAR

## Google Cloud

### Google Cloud Storage

1- Cloud Storage' girip yeni bir bucket oluştur. \
2- Erişim için IAM & Admin kısmından yeni bir yetkili tanımla \
3- İlgili kullanıcı detaylara girerek için KEY dosyası oluştur \
4- Bu dosyayı uygulamanın root kalsorunde tutabiliriz ya da farklı bir konum da
5- System Environment için de bu dosyanın yolunu belirten bir değişken tanımlanır. 
Key: GOOGLE_APPLICATION_CREDENTIALS
Value: /home/user/xxxx.json\
6- applacation.yml dosyasında gerekli ayarlar yapılır. (OPTIONAL)
elle yetkilendirme yapmak için kullanılır. gerekli değildir.
spring:
    cloud:
        gcp:
            project-id: "endless-gamma-191414"
            credentials:
            scopes: https://www.googleapis.com/auth/devstorage.read_write
  \



