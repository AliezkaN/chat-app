import {Component, ElementRef, ViewChild} from '@angular/core';
import {Router} from "@angular/router";
import {WebsocketService} from '../../services/services/websocket.service';
import {AuthenticationService} from "../../services/services/authentication.service";
import {HistoryService} from "../../services/services/history.service";
import {finalize} from "rxjs";

@Component({
  selector: 'app-chatroom',
  templateUrl: './chatroom.component.html',
  styleUrls: ['./chatroom.component.scss']
})
export class ChatroomComponent {
  constructor(
    private router: Router,
    private websocketService: WebsocketService,
    private authenticationService: AuthenticationService,
    private historyService: HistoryService
  ) {
  }

  @ViewChild('messageArea') messageArea!: ElementRef;

  username = '';
  message = '';
  messages: any[] = [];
  isConnected = false;

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  private scrollToBottom(): void {
    try {
      this.messageArea.nativeElement.scrollTop = this.messageArea.nativeElement.scrollHeight;
    } catch (err) {
      console.error('Failed to scroll to bottom:', err);
    }
  }

  // Called on each input event to update the counter
  updateCharacterCount(): void {
    if (this.message.length > 200) {
      this.message = this.message.slice(0, 200);
    }
  }

  ngOnInit(): void {
    this.authenticationService.whoAmI().subscribe(name => {
      this.username = name as string;

      this.connect()
    });
  }

  connect(): void {
    console.log(this.username)
    if (this.username.trim()) {
      this.isConnected = true;

      this.websocketService.connect(this.username);
      this.historyService.getHistory()
        .pipe(
          finalize(() => {
            // Start listening to WebSocket messages after history is loaded
            this.websocketService.messageSubject.subscribe({
              next: value => {
                if (value) this.messages.push(value);
              },
              error: err => console.error('Observable emitted an error: ' + err),
            });
          })
        )
        .subscribe({
          next: value => {
            this.messages = value
              .sort((a, b) => new Date(a.dateTime).getTime() - new Date(b.dateTime).getTime());
          },
            error: err => console.error(err)
        })
    }
  }

  sendMessage(): void {
    if (this.message.trim()) {
      this.websocketService.sendMessage(this.username, this.message);
      this.message = '';
    }
  }

  logout() {
    localStorage.clear()
    this.router.navigate(['login'])
  }
}
