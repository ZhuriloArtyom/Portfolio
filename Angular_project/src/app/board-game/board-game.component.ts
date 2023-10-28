import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BoardGame } from '../board-game';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-board-game',
  standalone: true,
  imports: [RouterModule,
    CommonModule],
  template: `
  <section class="listing">
    <img class="listing-photo" [src]="boardGame.photo" alt="{{boardGame.name}}">
    <h2 class="listing-heading">{{ boardGame.name }}</h2>
    <p class="listing-genre">{{ boardGame.genre}}</p>
    <a [routerLink] = "['/details', boardGame.id]">Learn More</a>
  </section> 
  `,
  styleUrls: ['./board-game.component.css']
})
export class BoardGameComponent {
  @Input() boardGame!: BoardGame;
}
