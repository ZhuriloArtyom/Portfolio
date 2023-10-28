import { Injectable } from '@angular/core';
import { BoardGame } from './board-game';

@Injectable({
  providedIn: 'root'
})
export class BoardGameService {
	boardGameList: BoardGame[] = [
    {
      id: 0,
      name: 'Brass: Birmingham ',
      genre: 'Economic Strategy',
      photo: '/assets/brass.webp',
      publisher: 'Roxley',
      releaseYear: 2018,
      playerCount: "2–4"
    },
     {
      id: 1,
      name: 'War of the Ring: Second Edition',
      genre: 'Wargame',
      photo: '/assets/wotr.webp',
      publisher: ' Ares Games',
      releaseYear: 2011,
      playerCount: "2"
    },
    {
      id: 2,
      name: 'Great Western Trail',
      genre: 'Economic Strategy',
      photo: '/assets/trail.webp',
      publisher: ' eggertspiele',
      releaseYear: 2016,
      playerCount: "2-4"
    },
    {
      id: 3,
      name: 'A Feast for Odin',
      genre: 'Economic Strategy',
      photo: '/assets/odin.webp',
      publisher: 'Feuerland Spiele ',
      releaseYear: 2016,
      playerCount: "1–4"
    },
    {
      id: 4,
      name: 'Wingspan',
      genre: 'Strategy',
      photo: '/assets/wingspan.webp',
      publisher: 'Stonemaier Games ',
      releaseYear: 2019,
      playerCount: "1–5"
    },
    {
      id: 5,
      name: 'Barrage',
      genre: 'Economic Strategy',
      photo: '/assets/barrage.webp',
      publisher: 'Cranio Creations',
      releaseYear: 2019,
      playerCount: "1–4"
    },
    {
      id: 6,
      name: 'Puerto Rico',
      genre: 'Economic Strategy',
      photo: '/assets/puerto.webp',
      publisher: ' alea',
      releaseYear: 2002,
      playerCount: "3-5"
    },
    {
      id: 7,
      name: 'Too Many Bones',
      genre: 'Adventure Strategy',
      photo: '/assets/bones.webp',
      publisher: 'Chip Theory Games ',
      releaseYear: 2017,
      playerCount: "1-4"
    },
  ];
  
  getAllBoardGames(): BoardGame[] {
  return this.boardGameList;
	}

	getBoardGameById(id: number): BoardGame | undefined {
  return this.boardGameList.find(boardGame => boardGame.id === id);
}}
