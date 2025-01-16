import {HttpClient, HttpContext, HttpResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {StrictHttpResponse} from "../../strict-http-response";
import {RequestBuilder} from "../../request-builder";
import {filter, map} from "rxjs/operators";
import {Message} from "../../models/history/message";

export function history(http: HttpClient, rootUrl: string, context?: HttpContext) : Observable<StrictHttpResponse<Message[]>> {
  const rb = new RequestBuilder(rootUrl, history.PATH, 'get');
  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Message[]>;
    })
  );
}

history.PATH = '/history'
