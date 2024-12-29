const express = require('express');
const cors = require('cors');
const app = express();

app.use(cors()); // 允许所有源进行跨域访问

// 或者您可以配置具体的源
app.use(cors({
    origin: 'http://localhost:5173', // 只允许来自 http://localhost:5173 的请求
    methods: ['GET', 'POST'], // 允许的 HTTP 方法
    allowedHeaders: ['Content-Type', 'Authorization'], // 允许的请求头
}));