const dom = (function () {

    var formId = "";

    const billByUserFormObj = {
        userLogin: {
            label: 'User login',
            type: 'text',
            class: 'form-control',
            placeholder: 'Login',
            name: 'Login',
            id: 'Login-input',
        },
        action: {
            type: 'hidden',
            name: 'action',
            value: 'bill',
        },
        submitButton: {
            type: 'submit',
            class: 'btn btn-primary',
            value: 'Submit'
        }
    };

    const drinkFormObj = {
        drinkNo: {
            label: 'Drink No',
            type: 'number',
            class: 'form-control',
            placeholder: 'Drink No',
            name: 'DrinkNo',
            min: '1',
            step: '1',
            id: 'DrinkNo-input',
        },
        drinkPortions: {
            label: 'Portions',
            type: 'number',
            class: 'form-control',
            placeholder: 'Drink Portions',
            name: 'DrinkPortions',
            min: '0',
            step: '1',
            id: 'DrinkPortions-input',
        },
        action: {
            type: 'hidden',
            name: 'action',
            value: 'buy_drink',
        },
        submitButton: {
            type: 'submit',
            class: 'btn btn-primary',
            value: 'Submit'
        }
    };

    const fillFormObj = {
        ingredientNo: {
            label: 'Ingredient No',
            type: 'number',
            class: 'form-control',
            placeholder: 'Ingredient No',
            name: 'IngredientNo',
            min: '1',
            step: '1',
            id: 'IngredientNo-input',
        },
        fillAmount: {
            label: 'Fill Amount',
            type: 'number',
            class: 'form-control',
            placeholder: 'The amount',
            name: 'FillAmount',
            min: '0',
            step: '0.001',
            id: 'FillAmount-input',
        },
        action: {
            type: 'hidden',
            name: 'action',
            value: 'fill',
        },
        submitButton: {
            type: 'submit',
            class: 'btn btn-primary',
            value: 'Submit'
        }
    };

    
    function buildForm(form, type) {
        var formObj;
        switch (type) {
            case 'bill-by-user-form':
                formObj = billByUserFormObj;
                break;
            case 'drink-form':
                formObj = drinkFormObj;
                break;
            case 'fill-form':
                formObj = fillFormObj;
                break;  
        }
        const inputAttributes = 
        [
            'type',
            'class',
            'placeholder',
            'name',
            'min',
            'step',
            'pattern',
            'readOnly',
            'id' 
        ];
        for (const property in formObj) {
            switch (formObj[property].type) {
                case 'submit':
                    const submit = document.createElement('input');
                    submit.setAttribute('type', formObj[property].type);
                    submit.setAttribute('class', formObj[property].class);
                    submit.value = formObj[property].value;

                    form.appendChild(submit);
                    break;
                case 'hidden':
                    const hidden = document.createElement('input');
                    hidden.setAttribute('name', formObj[property].name);
                    hidden.setAttribute('value', formObj[property].value);
                    hidden.setAttribute('type', formObj[property].type);

                    form.appendChild(hidden);
                    break;
		default:
                    const formDiv = document.createElement('div');
                    formDiv.setAttribute('class', 'form-group');

                    const label = document.createElement('label');
                    label.innerHTML = formObj[property].label;
                    formDiv.appendChild(label);

                    const input = document.createElement('input');
		    for (const inputAttribute of inputAttributes) {
                        if (formObj[property].hasOwnProperty(inputAttribute)) {
                            input.setAttribute(inputAttribute, formObj[property][inputAttribute]);
                        }
                    }
                    input.required = true;
                    formDiv.appendChild(input);

                    form.appendChild(formDiv);
                    break;
            }
        }
    }

    function initPage() {

	var form;
	const forms = 
        [
            'bill-by-user-form',
            'drink-form',
            'fill-form'
        ];
        for (const id of forms) {
            form = document.getElementById(id);
            if (form != null) {
                buildForm(form, id);
	            formId = id;
            }
	    }
    }

    return {
        initPage
    }

}());

dom.initPage();
