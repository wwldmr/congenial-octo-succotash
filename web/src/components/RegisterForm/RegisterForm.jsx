import { useState } from 'react';
import styles from './RegisterForm.module.css';

const RegisterForm = ({ onClose }) => {
  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    email: ''
  });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/employees/create', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData)
      });

      if (!response.ok) throw new Error('Ошибка регистрации');

      alert('Регистрация успешна!');
      onClose();
    } catch (error) {
      alert(error.message);
    }
  };

  return (
    <div className={styles.modal}>
      <h3>Регистрация</h3>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Имя"
          value={formData.firstName}
          onChange={(e) => setFormData({...formData, firstName: e.target.value})}
          required
          className={styles.input}
        />
        <input
          type="text"
          placeholder="Фамилия"
          value={formData.lastName}
          onChange={(e) => setFormData({...formData, lastName: e.target.value})}
          required
          className={styles.input}
        />
        <input
          type="email"
          placeholder="Email"
          value={formData.email}
          onChange={(e) => setFormData({...formData, email: e.target.value})}
          required
          className={styles.input}
        />
        <div className={styles.buttons}>
          <button type="submit" className={styles.button}>
            Зарегистрироваться
          </button>
          <button type="button" onClick={onClose} className={styles.button}>
            Отмена
          </button>
        </div>
      </form>
    </div>
  );
};

export default RegisterForm;