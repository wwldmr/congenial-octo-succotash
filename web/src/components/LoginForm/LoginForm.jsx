import { useState } from 'react';
import styles from './LoginForm.module.css';

const LoginForm = ({ onClose }) => {
  const [email, setEmail] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`http://localhost:8080/api/employees/get_email/${email}`);

      if (!response.ok) throw new Error('Пользователь не найден');

      const user = await response.json();
      alert(`Добро пожаловать, ${user.firstName}!`);
      onClose();
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div className={styles.modal}>
      <h3>Вход</h3>
      <form onSubmit={handleSubmit}>
        <input
          type="email"
          placeholder="Введите ваш email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
          className={styles.input}
        />
        <div className={styles.buttons}>
          <button type="submit" className={styles.button}>
            Войти
          </button>
          <button type="button" onClick={onClose} className={styles.button}>
            Отмена
          </button>
        </div>
      </form>
    </div>
  );
};

export default LoginForm;