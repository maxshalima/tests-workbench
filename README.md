Шаблон для простых проектов с ипользованием spring-boot и Angular.
====================================================================

## Запуск

Для запуска нужен установленный в системе Docker с плагином compose, либо для Windows/MacOS - Docker Desktop.

```sh
cd docker
docker compose build
docker compose up
```
Открыть брауер по ссылки [http://localhost](http://localhost])

## Разработка
Для сборки проекта вне контейнера понадобятся Java 21 и NodeJS 22.19.0.
### API
Построен на базе spring-boot 3.5, используются spring-data-jpa, spring-webmvc, mapstruct, lombok
### UI
Пострен на основе шаблона [sakai-ng 20.0.0](https://github.com/primefaces/sakai-ng)
