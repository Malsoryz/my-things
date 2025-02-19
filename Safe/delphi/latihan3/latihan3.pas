unit latihan3;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls, Vcl.Buttons;

type
  TForm1 = class(TForm)
    StaticText2: TStaticText;
    Hasil: TStaticText;
    Bilangan1column: TEdit;
    StaticText1: TStaticText;
    Hasilcolumn: TEdit;
    Bilangan2column: TEdit;
    kalibtn: TBitBtn;
    tambahbtn: TBitBtn;
    bagibtn: TBitBtn;
    kurangbtn: TBitBtn;
    procedure tambahbtnClick(Sender: TObject);
    procedure kalibtnClick(Sender: TObject);
    procedure bagibtnClick(Sender: TObject);
    procedure kurangbtnClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
a, b: real;
  Form1: TForm1;

implementation

{$R *.dfm}

procedure TForm1.bagibtnClick(Sender: TObject);
begin
a := strtofloat(Bilangan1column.Text);
b := strtofloat(Bilangan2column.Text);
Hasilcolumn.Text := floattostr(a / b);
end;

procedure TForm1.kalibtnClick(Sender: TObject);
begin
a := strtofloat(Bilangan1column.Text);
b := strtofloat(Bilangan2column.Text);
Hasilcolumn.Text := floattostr(a * b);
end;

procedure TForm1.kurangbtnClick(Sender: TObject);
begin
a := strtofloat(Bilangan1column.Text);
b := strtofloat(Bilangan2column.Text);
Hasilcolumn.Text := floattostr(a - b);
end;

procedure TForm1.tambahbtnClick(Sender: TObject);
begin
a := strtofloat(Bilangan1column.Text);
b := strtofloat(Bilangan2column.Text);
Hasilcolumn.Text := floattostr(a + b);
end;

end.
