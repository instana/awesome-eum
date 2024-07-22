import express from 'express';
import * as jsonServer from 'json-server';

import generateDB from './dataGenerator';

const db = generateDB();

const server = jsonServer.create();
const router = jsonServer.router(db);
const middlewares = jsonServer.defaults();

server.use(middlewares);
server.use(router);

// app.get('/', (_req, res) => {
//   res.end(JSON.stringify(db));
// });

server.listen('3900', () => {
  console.log('listen at 3900');
});
