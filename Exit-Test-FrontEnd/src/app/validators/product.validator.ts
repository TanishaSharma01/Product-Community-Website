import {
  AbstractControl,
  FormGroup,
  ValidationErrors,
  ValidatorFn,
} from '@angular/forms';

export const productValidator = (controlName: String): ValidatorFn => {
  return (control: AbstractControl) => {
    const value = control.value;
    switch (controlName) {
      case 'code':
        {
          // check length
          if (value.length < 3 && value.length > 0)  {
            return { message: 'Product must be at least 3 characters' };
          }
          if (value.length > 10) {
            return { message: 'Product must be less than 10 characters' };
          }
          return null;
        }
        break;
      case 'name':
        {
          // check length
          if (value.length < 3 && value.length>0) {
            return { message: 'Product must be at least 3 characters' };
          }
          if (value.length > 40) {
            return { message: 'Lastname must be less than 40 characters' };
          }
          return null;
        }
        break;
      case 'brand':
        {
          // check length
          if (value.length < 3 && value.length>0) {
            return { message: 'Product brand must be at least 3 characters' };
          }
          if (value.length > 30) {
            return { message: 'Product brand must be less than 30 characters' };
          }
          return null;
        }
        break;
    }
    return null;
  };
};
