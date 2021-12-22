import React from 'react';
import MediaList from './MediaList';
import Navbar from 'react-bootstrap/Navbar';
import FormControl from 'react-bootstrap/FormControl';
import Swal from 'sweetalert2';
import withReactContent from 'sweetalert2-react-content';

const MySwal = withReactContent(Swal);

export default class App extends React.Component {

  constructor(props) {
    super(props);
    this.state = {mediaList: {}, mediaListFiltered: []};
    this.filter = this.filter.bind(this);
  }

  componentDidMount() {
    MySwal.fire({
      title: 'Carregando...',
      onBeforeOpen: () => {
        MySwal.showLoading()
      }
    });

    fetch(window.API_URL + '/available/media')
      .then(response => response.json())
      .then(json => {
        this.setState({mediaList : json, mediaListFiltered: json.files});
        MySwal.close();
      })
      .catch(err => {
        MySwal.close();
        MySwal.fire({
          title: 'Erro ao obter dados!',
          type: 'error'
        });
      });
    
  }

  filter(event) {
    if (this.state.mediaList && this.state.mediaList.files) {
      console.log('evento: ' + event.target.value);
      if (event.target.value) {
        this.setState({
          mediaListFiltered: this.state.mediaList.files
            .filter(media => media.directory || media.fileName.toLowerCase().includes(event.target.value.toLowerCase()))});
      } else {
        this.setState({mediaListFiltered: this.state.mediaList.files});
      }
    }
  }

  render() {
    let content = null;
    if (this.state.mediaListFiltered && this.state.mediaListFiltered.length > 0) {
      content = (
        <MediaList mediaList={this.state.mediaListFiltered} />
      );
    }

    return (
      <div>
        <Navbar fixed="top" className="justify-content-between bg-dark">
          <Navbar.Brand className="text-light">
            LMG
          </Navbar.Brand>
          <FormControl type="text" placeholder="Buscar"
            onChange={this.filter} className="bg-dark text-light mr-sm-2"
            style={{/*width: '89vw',*/}} />
        </Navbar>
        <div className="container">
          {content}
        </div>
      </div>
    );
  }
}

