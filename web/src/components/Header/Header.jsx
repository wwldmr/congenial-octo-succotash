import { useState } from 'react';
import RegisterForm from '../RegisterForm/RegisterForm';
import LoginForm from '../LoginForm/LoginForm';
import styles from './Header.module.css';

const Header = () => {
  const [showRegister, setShowRegister] = useState(false);
  const [showLogin, setShowLogin] = useState(false);

  return (
    <header className={styles.header}>
      <button
        className={styles.button}
        onClick={() => {
          setShowRegister(true);
          setShowLogin(false);
        }}
      >
        Регистрация
      </button>
      <button
        className={styles.button}
        onClick={() => {
          setShowLogin(true);
          setShowRegister(false);
        }}
      >
        Войти
      </button>

      {showRegister && <RegisterForm onClose={() => setShowRegister(false)} />}
      {showLogin && <LoginForm onClose={() => setShowLogin(false)} />}
    </header>
  );
};

export default Header;