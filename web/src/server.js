const express = require('express');
const cors = require('cors');

const app = express();

// Разрешаем запросы с фронтенда
app.use(cors({
  origin: 'http://localhost:5137',
  methods: ['GET', 'POST', 'PUT', 'DELETE'],
  credentials: true
}));

//// Ваши роуты
//app.post('/api/v1/admin/login', (req, res) => {
//  // ... ваша логика
//});
//
//app.listen(4000, () => {
//  console.log('Server running on port 4000');
//});