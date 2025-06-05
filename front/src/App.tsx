import { useState } from 'react'
import './App.css'

function App() {
  const [file, setFile] = useState<File | null>(null)
  const [response, setResponse] = useState<any>(null)
  const [loading, setLoading] = useState(false)
  const [excelPath, setExcelPath] = useState<string>('')


  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const selectedFile = event.target.files?.[0]
    if (selectedFile && selectedFile.type === 'application/pdf') {
      setFile(selectedFile)
    } else {
      alert('Proszę wybrać plik PDF.')
      setFile(null)
    }
  }

  const handleClear = () => {
    setResponse(null)
  }

  return (
    <div className='PDFUploader'>
      <form className='card' onSubmit={(e) => {
        if (loading) return
        setLoading(true)
        e.preventDefault()
        if (file) {
          const formData = new FormData()
          formData.append('file', file)
          formData.append('excelPath', excelPath.startsWith('"') && excelPath.endsWith('"') ? excelPath.slice(1, -1) : excelPath)


          fetch('http://localhost:8080/api/pdf/upload', {
            method: 'POST',
            body: formData
          })
            .then(response => response.json())
            .then(data => {
              console.log('Success:', data)
              setResponse(data)
              setLoading(false)

              const fileInput = document.getElementById('fileInput') as HTMLInputElement
              if (fileInput) fileInput.value = ''
              setFile(null)
            })
            .catch((error) => {
              console.error('Error:', error)
              setResponse({ error: 'Wystąpił błąd podczas przesyłania pliku.' })
            })
        }
      }}>
        <div>
          <h1>Wybierz plik PDF</h1>
          <input id="fileInput" type="file" accept='.pdf' onInput={handleFileChange} />
        </div>

        <h1>Podaj scieżkę do pliku Excel</h1>
        <h2>Klik prawy na plik i "kopiuj jako ścieżkę"</h2>
        <p>Przykład: "C:\Users\micha\Desktop\Baza AI ZP\Baza_do_wyslania_copy.xlsx"</p>
        <input
          id='excelPathInput'
          type="text"
          value={excelPath}
          onChange={(e) => setExcelPath(e.target.value)}
          placeholder="Podaj sciezke do pliku Excel"
        />

        {file && excelPath != '' && !loading && (
          <button type="submit">Prześlij</button>)
        }

      </form>
      {loading && <div className='card'>Przetwarzanie pliku...</div>}

      {response && (
        <div className='card'>
          <div>
            <h2>Odpowiedź serwera:</h2>
            <p>Status: {response.status}</p>
            <p>Message: {response.message}</p>
            <p>Count: {response.count}</p>
          </div>
          <div>
            <button onClick={handleClear}>Wyczyść</button>
          </div>
        </div>
      )}



      </div>
      )
}

      export default App
