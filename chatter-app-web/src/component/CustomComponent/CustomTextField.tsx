import { useFormContext, Controller } from 'react-hook-form';
import { TextField } from '@mui/material';

const CustomTextField = ({props}: any) => {
  const { control } = useFormContext() ?? {};
  if (!control) return null;
  const name = props && props.name? props.name : '';
  const helperText = props && props.helperText? props.helperText : '';
  console.log(props);
  return (
    <Controller
      name={name}
      control={control}
      render={({ field, fieldState: { error } }) => (
        <TextField
          {...field}
          fullWidth
          
          value={typeof field.value === 'number' && field.value === 0 ? '' : field.value}
          error={!!error}
          helperText={error !== undefined? (error.message !== undefined? error.message: '') : (helperText !== undefined ? helperText: '')}
          {...props?.other}
        />
      )}
    />
  );
}

export default CustomTextField;
