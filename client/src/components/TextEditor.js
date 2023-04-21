import styled from 'styled-components';
import { CKEditor } from '@ckeditor/ckeditor5-react';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { useState } from 'react';


function TextEditor() {
    const [text, setText] = useState('');
  
    return (
        <EditorContainer>
            <Editor>
                <CKEditor
                    editor={ClassicEditor}
                    date={text}
                    config={{
                        contentCss: '/path/to/custom.css',
                    }}
                    onChange={(event, editor) => {
                        const data = editor.getData();
                        setText(data)}
                    }
                />
            </Editor>
        </EditorContainer>
    )
  }
  
export default TextEditor;

const EditorContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
`;

const Editor = styled.div`
  .ck-editor__main {
    width: 80%;
    height: 80%;
  }

  .ck-toolbar {
    height: 30px;
  }

  .ck-toolbar button {
    font-size: 80%;
  }
`;