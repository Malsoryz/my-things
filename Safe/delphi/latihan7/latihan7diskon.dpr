program latihan7diskon;

uses
  Vcl.Forms,
  diskon in 'diskon.pas' {flatihan7};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(Tflatihan7, flatihan7);
  Application.Run;
end.
