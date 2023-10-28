import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { BoardGameService } from '../board-game.service';
import { BoardGame } from '../board-game';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [
  CommonModule,
],
  template: `
  <article>
    <img class="photo" [src]="boardGame?.photo"
      alt="Exterior photo of {{boardGame?.name}}"/>
    <section class="listing-description">
      <h2 class="listing-heading">{{boardGame?.name}}</h2>
      <p class="listing-location">{{boardGame?.genre}}</p>
    </section>
    <section class="listing-features">
      <h2 class="section-heading">About this game</h2>
      <ul>
        <li>Publisher: {{boardGame?.publisher}}</li>
        <li>Player count: {{boardGame?.playerCount}}</li>
        <li>Release year: {{boardGame?.releaseYear}}</li>
      </ul>
    </section>
  </article>
`,
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {
    route: ActivatedRoute = inject(ActivatedRoute);
    boardGameService = inject(BoardGameService);
  	boardGame: BoardGame | undefined;
    boardGameId = -1;

    constructor() {
        this.boardGameId = Number(this.route.snapshot.params['id']);
        this.boardGame = this.boardGameService.getBoardGameById(this.boardGameId);
  }
}  
