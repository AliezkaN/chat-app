import { HttpClient, HttpContext } from '@angular/common/http';
import {Injectable} from "@angular/core";
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { authenticate } from '../fn/authentication/authenticate';
import { Authenticate$Params } from '../fn/authentication/authenticate';
import { AuthenticationResponse } from '../models/auth/authentication-response';
import { register } from '../fn/authentication/register';
import { Register$Params } from '../fn/authentication/register';
import {whoAmI} from "../fn/authentication/whoami";

@Injectable({ providedIn: 'root' })
export class AuthenticationService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  register$Response(params: Register$Params, context?: HttpContext): Observable<StrictHttpResponse<{}>> {
    return register(this.http, this.rootUrl, params, context);
  }

  register(params: Register$Params, context?: HttpContext): Observable<{}> {
    return this.register$Response(params, context).pipe(
      map((r: StrictHttpResponse<{}>): {} => r.body)
    );
  }

  authenticate$Response(params: Authenticate$Params, context?: HttpContext): Observable<StrictHttpResponse<AuthenticationResponse>> {
    return authenticate(this.http, this.rootUrl, params, context);
  }

  authenticate(params: Authenticate$Params, context?: HttpContext): Observable<AuthenticationResponse> {
    return this.authenticate$Response(params, context).pipe(
      map((r: StrictHttpResponse<AuthenticationResponse>): AuthenticationResponse => r.body)
    );
  }


  whoAmI$Response(context?: HttpContext): Observable<StrictHttpResponse<String>> {
    return whoAmI(this.http, this.rootUrl, context);
  }

  whoAmI(context?: HttpContext): Observable<String> {
    return this.whoAmI$Response(context).pipe(
      map((r: StrictHttpResponse<String>): String => r.body)
    )
  }
}
