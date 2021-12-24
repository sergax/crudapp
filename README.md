Практика:

Необходимо реализовать консольное CRUD приложение, которое имеет следующие сущности:

Writer(id, name, List<Post> posts)
Post(id, content, List<Tag> tags, PostStatus status)
Tag(id, name)
PostStatus (enum ACTIVE, DELETED)

В качестве хранилища данных необходимо использовать текстовые файлы:
writers.json, posts.json, tags.json
Пользователь в консоли должен иметь возможность создания, получения, редактирования и удаления данных.
Слои:
model - POJO классы
repository - классы, реализующие доступ к текстовым файлам
controller - обработка запросов от пользователя
view - все данные, необходимые для работы с консолью

Например: Writer, WriterRepository, WriterController, WriterView и т.д.
Для репозиторного слоя желательно использовать базовый интерфейс:
interface GenericRepository<T,ID>



interface WriterRepository extends GenericRepository<Writer, Long>

class GsonWriterRepositoryImpl implements WriterRepository

Результатом выполнения задания должен быть отдельный репозиторий с README.md файлом, который содержит описание задачи, проекта и инструкции запуска приложения через командную строку.
Для работы с JSON необходимо использовать библиотеку Gson.
Для импорта зависимостей - Maven/Gradle на выбор

Инструкция по запуску приложения :

* Зайти на репозиторий и скачать .zip проекта
* Распаковать архив и открыть в intellij idea
* Запустить класс ConsoleRunner

