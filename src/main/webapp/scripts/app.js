

function validateReg() {
    const name = $('#name').val();
    const email = $('#email').val();
    const pass = $('#password').val();
    const phone = $('#phone').val();
    if (name === '' || email === '' || pass === '' || phone === '') {
        alert('All fields are required!');
        return false;
    }
}

function validateLogin() {
    const email = $('#email').val();
    const pass = $('#password').val();
    if (email === '' ) {
        alert('Please enter email!');
        return false;
    } else if (pass === '') {
        alert('Please enter password!');
        return false;
    }
}

function validatePost() {
    const model = $('#model').val();
    const hp = $('#hp').val();
    const mileage = $('#mileage').val();
    const year = $('#year').val();
    const color = $('#color').val();
    const price = $('#price').val();
    if (model === '' ) {
        alert('Please enter the model!');
        return false;
    } else if(!/^[0-9]+$/.test(hp)){
        alert('Please enter horsepower!')
        return false;
    } else if(!/^[0-9]+$/.test(mileage)){
        alert('Please enter mileage!')
        return false;
    } else if(!/^[0-9]+$/.test(year)){
        alert('Please enter year!')
        return false;
    } else if (color === '' ) {
        alert('Please enter color!');
        return false;
    } else if(!/^[0-9]+$/.test(price)){
        alert('Please enter price!')
        return false;
    }
}
