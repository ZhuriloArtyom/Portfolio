import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BoardGameComponent } from '../board-game/board-game.component';
import { BoardGame } from '../board-game';
import { BoardGameService } from '../board-game.service';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
  BoardGameComponent],
  template: `
   <section>
    <form>
      <input type="text" placeholder="Search" #filter>
      <button class="primary" type="button"
      (click)="filterResults(filter.value)">Search</button>
    </form>
  </section>
  <section class="results">
   	<app-board-game *ngFor="let boardGame of filteredGameList"
   	 [boardGame]="boardGame"></app-board-game>
  </section>`,
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
 	boardGameList: BoardGame[] = [];
	boardGameService: BoardGameService = inject(BoardGameService);
	filteredGameList: BoardGame[] = [];
	favouriteOn = false;
	favouriteGameList: BoardGame[] = [];
	constructor() {
	  this.boardGameList = this.boardGameService.getAllBoardGames();
	  this.favouriteGameList = this.boardGameList;
	  this.filteredGameList = this.boardGameList;
	}
	filterResults(text: string) {
  if(this.favouriteOn){
     this.favouriteGameList = this.boardGameList.filter(
    boardGame => boardGame?.name.toLowerCase().includes(text.toLowerCase())
  );}
  else this.favouriteGameList = this.boardGameList;
    
  if (!text) {
     this.filteredGameList = this.favouriteGameList;
  }

  this.filteredGameList = this.favouriteGameList.filter(
    boardGame => boardGame?.name.toLowerCase().includes(text.toLowerCase())
  );
	}
}
