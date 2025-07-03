# Telegram Ping Bot

Це Telegram-бот з підтримкою Mini App (WebApp), який дозволяє користувачам надсилати "пінг" адміну через кнопку в WebApp.

---

## Функціонал

- Користувач надсилає команду `/start`
- Отримує кнопку **"Open Mini App"**
- Відкривається WebApp з кнопкою **"Ping Admin"**
- При натисканні — адміністратору надходить повідомлення:  
  **"Користувач Ім’я (ID) натиснув кнопку Ping!"**
- Усі користувачі зберігаються у базу PostgreSQL
- Ролі: `CLIENT`, `ADMINISTRATOR`

---

## Технології

- Kotlin + Spring Boot
- Telegram Bots API
- Telegram WebApps (JS)
- PostgreSQL
- Docker + Docker Compose

---

## Як запустити

### 1. Клонувати репозиторій


git clone https://github.com/Mykolayovych/ping-bot.git
cd ping-bot

### 2. Замінити змінні середовища на Ваші

BOT_TOKEN=ваш_токен_бота_від_BotFather

ADMIN_IDS=ваш_telegram_id

TELEGRAM_WEBAPP_URL=https://your-ngrok-id.ngrok-free.app/webapp

### 3. Створити тунель для WebApp (через ngrok)

ngrok http 8080

### 4. Запустити додаток

docker-compose up --build



