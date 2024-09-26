import {
    AbstractControl,
    FormGroup,
    ValidationErrors,
    ValidatorFn,
  } from '@angular/forms';
  
  export const reviewValidator = (controlName: String): ValidatorFn => {
    return (control: AbstractControl) => {
      const value = control.value;
      switch (controlName) {
        case 'title':
          {
            // required
            if (value === null || value === undefined || value.length === 0) {
              return { required: true };
            }
  
            // check length
            if (value.length < 5) {
              return { message: 'Heading must be at least 5 characters' };
            }
            if (value.length > 30) {
              return { message: 'Heading must be less than 30 characters' };
            }
            return null;
          }
          break;
        case 'description':
          {
            // required
            if (value.length === 0) {
              return { required: true };
            }
  
            // check length
            if (value.length < 20) {
              return { message: 'Description must be at least 20 characters' };
            }
            if (value.length > 200) {
              return { message: 'Description must be less than 200 characters' };
            }
            return null;
          }
          break;
        case 'rating':
          {
            // required
            if (value === 0) {
              return { required: true };
            }
  
            if (value <= 0 || value > 5) {
              return { message: 'Invalid rating value' };
            }
            return null;
          }
          break;
      }
      return null;
    };
  };
  