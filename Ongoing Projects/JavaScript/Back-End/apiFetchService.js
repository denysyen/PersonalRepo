import * as Api from './fetch_service.js'
import * as fs from 'fs';

const films = await Api.getAllMovies();

const series = await Api.getAllSeries();

const result = films.concat(series);

fs.writeFileSync('./src/localAPIModel.json', JSON.stringify(result))