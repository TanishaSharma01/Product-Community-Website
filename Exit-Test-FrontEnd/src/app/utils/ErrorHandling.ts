import { Observable, catchError, throwError } from 'rxjs';

function handleError(error:any){
    return throwError(error.message || "Server Error");
}

export {handleError};