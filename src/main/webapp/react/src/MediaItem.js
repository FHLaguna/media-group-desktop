import React from 'react';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';

const MySwal = withReactContent(Swal);

export default class MediaItem extends React.Component {

    constructor(props) {
        super(props);
        this.addMedia = this.addMedia.bind(this);
    }

    addMedia() {
        MySwal.fire({
            title: 'Deseja realmente adicionar ' + this.props.media.fileName + '?',
            type: 'question',
            confirmButtonText: 'Sim',
            confirmButtonColor: '#28a745',
            cancelButtonText: 'Não',
            cancelButtonColor: '#dc3545',
            showCancelButton: true,
            showCloseButton: true,
            showLoaderOnConfirm: true,
            preConfirm: () => {
                return fetch(window.API_URL + '/queue/media', {
                    method: 'PUT',
                    body: JSON.stringify({filePath: this.props.media.filePath}),
                    headers: {
                      'Content-Type': 'application/json'
                    }
                }).then(response => response.json())
                .then(resp => {
                    if (resp.responseCode === 0) {
                        return resp;
                    } else {
                        return new Error(resp.message);
                    }
                });
            },
            allowOutsideClick: () => !MySwal.isLoading()
        }).then(result => {
            if (result.value) {
                MySwal.fire({
                    title: 'Adicionado com sucesso!',
                    type: 'success'
                });
            }
        }).catch(err => {
            MySwal.fire({
                title: 'Erro: ' + err,
                type: 'error'
            });
        });
        
    }

    render() {
        return (
            <button className="list-group-item list-group-item-action text-light bg-dark" 
                onClick={this.addMedia}>
                {this.props.media.fileName}
            </button>
        );
    }

}