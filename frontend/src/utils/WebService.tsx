import axios from "./CustomizeApiFunction";

//Interact with users
const addUser = async (firstName, lastName, email, password, address, phoneNumber, image) => {
    try {
        const formData = new FormData();
        formData.append('firstName', firstName);
        formData.append('lastName', lastName);
        formData.append('email', email);
        formData.append('password', password);
        formData.append('address', address);
        formData.append('phoneNumber', phoneNumber);
        formData.append('image', image); // append the file to the form data

        // Make the POST request using Axios
        const response = await axios.post('/users/register/new-user', formData, {
            headers: {
                'Content-Type': 'multipart/form-data', // Make sure to set proper headers
            },
        });

        // Handle the response
        console.log('Response:', response.data);
        return response;
        // You can do further processing here, like updating state or showing a success message
    } catch (error) {
        // Handle errors
        console.error('Error:', error);
        // You can handle errors here, like showing an error message to the user
    }
}

const updateUserInfo = async (firstName, lastName, email, password, address, phoneNumber) => {
    const formData = new FormData();
    formData.append('firstName', firstName);
    formData.append('lastName', lastName);
    formData.append('email', email);
    formData.append('password', password);
    formData.append('address', address);
    formData.append('phoneNumber', phoneNumber);

    // Make the POST request using Axios
    const response = await axios.put(`/users/update/user-information/${email}`, formData);
    return response;
}

const updateUserAvatar = async (email, fileName, image) => {
    const formData = new FormData();
    formData.append('email', email);
    formData.append('fileName', fileName);
    formData.append('image', image); // append the file to the form data

    const response = await axios.put(`/users/update/user-avatar/${email}`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data', // Make sure to set proper headers
        },
    });
    return response
}

const deleteUser = async (email) => {
    const response = await axios.delete(`/users/delete/user-data/${email}`)
    return response
}
//End here


//Interact with products:
const addProduct = async (name, desc, price, stock, image) => {
    const formData = new FormData();
    formData.append("name", name);
    formData.append("desc", desc);
    formData.append("price", price);
    formData.append("stock", stock);
    formData.append("image", image);

    const response = await axios.post(`/products/register/new-user/${name}`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data', // Make sure to set proper headers
        },
    });
    return response
}

const updateProductInfo = async (name, desc, price, stock) => {
    const formData = new FormData();
    formData.append("desc", desc);
    formData.append("price", price);
    formData.append("stock", stock);

    const response = await axios.put(`/products/update/user-information/${name}`, formData)
    return response
}

const updateProductAvatar = async (name, fileName, image) => {
    const formData = new FormData();
    formData.append("fileName", fileName)
    formData.append("image", image)

    const response = await axios.put(`/products/update/user-avatar/${name}`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data', // Make sure to set proper headers
        },
    });
    return response
}


const deleteProduct = async (name) => {
    const response = axios.delete(`/products/delete/user-data/${name}`)
    return response
}




























export default {addUser, updateUserInfo, updateUserAvatar, deleteUser,
    addProduct, updateProductInfo, updateProductAvatar, deleteProduct
}