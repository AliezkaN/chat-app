import {Injectable} from "@angular/core";
import {BaseService} from "../base-service";
import {ApiConfiguration} from "../api-configuration";
import {HttpClient, HttpContext} from "@angular/common/http";
import {Observable} from "rxjs";
import {StrictHttpResponse} from "../strict-http-response";
import {Message} from "../models/history/message";
import {map} from "rxjs/operators";
import {history} from "../fn/history/get-history";

@Injectable({ providedIn: 'root' })
export class HistoryService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  getHistory$Response(context?: HttpContext): Observable<StrictHttpResponse<Message[]>> {
    return history(this.http, this.rootUrl, context);
  }

  getHistory(context?: HttpContext): Observable<Message[]> {
    return this.getHistory$Response(context).pipe(
      map(response => response.body as Message[])
    )
  }
}
