import {Client} from '@stomp/stompjs';
import {BehaviorSubject} from "rxjs";
import {Injectable} from "@angular/core";
import {TokenService} from "../token/token.service";
import {ApiConfiguration} from "../api-configuration";

export type ListenerCallBack = (message: Task) => void;

@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private stompClient: Client | null = null;
  public messageSubject = new BehaviorSubject<any>(null);

  constructor(
    private tokenService: TokenService,
    private config: ApiConfiguration
  ) {
  }


  connect(username: string): void {
    const token = this.tokenService.token;
    this.stompClient = new Client({
      brokerURL: this.config.rootUrl + this.config.websocketEndpoint, // Update to match your backend WebSocket endpoint
      connectHeaders: {"Authorization" : "Bearer " + token},
      debug: (str) => console.log(str),
      reconnectDelay: 5000, // Automatically reconnect
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });

    this.stompClient.onConnect = () => {
      console.log('Connected to WebSocket');
      this.stompClient?.subscribe('/topic/public', (message) => {
        console.log(message)
        this.messageSubject.next(JSON.parse(message.body));
      });

      this.stompClient?.publish({
        destination: '/app/chat.connect',
        body: JSON.stringify({initiator: username, type: 'JOIN'}),
      });
    };

    this.stompClient.onStompError = this.onError;
    this.stompClient.activate();
  }

  sendMessage(username: string, content: string): void {
    if (this.stompClient && content.trim()) {
      const chatMessage = {
        initiator: username,
        content,
        type: 'CHAT',
      };
      this.stompClient.publish({
        destination: '/app/chat.sendMessage',
        body: JSON.stringify(chatMessage),
      });
    }
  }

  private onError = (error: any): void => {
    console.error('WebSocket error:', error);
  };
}
